import React, {Component} from 'react'
import './App.css'
import PrivateRoute from './util/PrivateRoute'

import {ConfigProvider, Layout, notification} from 'antd'
import {Route, Switch, withRouter} from 'react-router-dom'

import {localizedStrings} from '../components/util/localization'
import {
    ACCESS_TOKEN_HEADER_KEY,
    LANGUAGE,
    REFRESH_TOKEN_HEADER_KEY,
    ROLE_ADMIN,
    ROLE_USER,
    SUCCESS,
    USER_ID
} from '../constants'
import AppHeader from '../components/common/header/AppHeader'
import LoadingIndicator from '../components/common/util/LoadingIndicator'
import OAuth2RedirectHandler from '../components/user/oauth2/OAuth2RedirectHandler'
import AppFooter from '../components/common/footer/AppFooter'
import NotFound from '../components/common/error/NotFound'
import Profile from '../components/user/profile/Profile'
import SignUp from '../components/user/signup/SignUp'
import Login from '../components/user/login/Login'
import Company from '../components/company/Company'


import {getCurrentCompanyRequest, getCurrentUserRequest} from '../components/util/utilsAPI'
import ShopsList from "../components/shop/ShopsList";
import Home from "../components/home/Home";
import ShopAdd from "../components/shop/ShopAdd";

const {Content} = Layout

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
            currentUser: null,

            currentCompany: null,

            isAuthenticated: false,
            isLoading: false,

            language: localStorage.getItem(LANGUAGE) === null ? "en" : localStorage.getItem(LANGUAGE)
        }

        notification.config({
            placement: "topRight",
            top: 70,
            duration: 2,
        })
    }

    handleLanguageChange = (lang) => {
        localStorage.setItem(LANGUAGE, lang)
        this.setState(() => ({
            language: lang
        }))
    }

    loadCurrentCompany = () => {

        this.setState({
            isLoading: true
        })
        getCurrentCompanyRequest()
            .then(response => {
                console.log('RESPONCE' + response)
                this.setState({
                    currentCompany: response,
                    isLoading: false
                })
            }).catch(() => {
            this.setState({
                isLoading: false
            })
        })

    }

    loadCurrentUser = () => {

        this.setState({
            isLoading: true
        })
        getCurrentUserRequest()
            .then(response => {
                this.setState({
                    currentUser: response,
                    isAuthenticated: true,
                    isLoading: false
                })
            }).catch(() => {
            this.setState({
                isLoading: false
            })
        })

    }

    componentDidMount() {
        this.loadCurrentUser()
        this.loadCurrentCompany()
    }

    handleLogout =
        (redirectTo = '/', notificationType = SUCCESS, description = localizedStrings.alertSuccessLogOut) => {

            localStorage.removeItem(ACCESS_TOKEN_HEADER_KEY)
            localStorage.removeItem(REFRESH_TOKEN_HEADER_KEY)

            localStorage.removeItem(USER_ID)

            this.setState({
                currentUser: null,
                isAuthenticated: false
            })

            this.props.history.push(redirectTo)

            notification[notificationType]({
                message: localizedStrings.alertAppName,
                description: description,
            })
        }

    handleLogin = () => {
        notification.success({
            message: localizedStrings.alertAppName,
            description: localizedStrings.alertSuccessLogin,
        })
        this.loadCurrentUser()
        this.props.history.push("/profile")
    }

    render() {
        if (this.state.isLoading) {
            return <LoadingIndicator/>
        }

        localizedStrings.setLanguage(this.state.language)

        return (
            <>
            <Layout className="app-wrapper">
                <AppHeader isAuthenticated={this.state.isAuthenticated}
                           currentUser={this.state.currentUser}
                           handleLogout={this.handleLogout}
                />


                <Content className="app-content">
                    <div className="site-layout-background" style={{ minHeight: 380}}>
                        <Switch>


                            <Route exact path="/login"
                                   render={(props) =>
                                       <Login onLogin={this.handleLogin}
                                              {...props} />}/>

                            <Route path="/sign-up"
                                   render={(props) =>
                                       <SignUp
                                           isAuthenticated={this.state.isAuthenticated}
                                           {...props} />}/>

                            <Route path="/oauth2/redirect"
                                   render={(props) =>
                                       <OAuth2RedirectHandler onLogin={this.handleLogin}
                                                              {...props} />}/>

                            <PrivateRoute path="/profile"
                                          isAuthenticated={this.state.isAuthenticated}
                                          currentUser={this.state.currentUser}
                                          component={Profile}/>


                            <Route path="/company/shops"
                                   currentCompany={this.state.currentCompany}
                                   component={ShopsList}/>


                            <Route path="/company"
                                   currentCompany={this.state.currentCompany}
                                   component={Company}/>

                            <Route path="/"
                                   component={Home}/>

                            <Route component={NotFound}/>

                        </Switch>
                    </div>

                </Content>
            </Layout>
        <AppFooter/>
        </>
        )
    }
}

export function isAdmin(currentUser) {
    console.log('is admin method works')


    if (currentUser !== null && currentUser !== undefined && currentUser.roles !== undefined) {

        console.log('currentUser email= ' + currentUser.email)

        const role = currentUser.roles.find(elem => elem.name === ROLE_ADMIN)

        return role === undefined ? false : role.name === ROLE_ADMIN
    }
    return false
}

export function isUser(currentUser) {
    if (currentUser !== null && currentUser !== undefined && currentUser.roles !== undefined) {
        const role = currentUser.roles.find(elem => elem.name === ROLE_USER)
        return role === undefined ? false : role.name === ROLE_USER
    }
    return false
}

export default withRouter(App)

