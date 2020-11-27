import React, {Component} from 'react'
import './App.css'
import PrivateRoute from './util/PrivateRoute'

import {Layout, notification} from 'antd'
import {Route, Switch, withRouter} from 'react-router-dom'

import {localizedStrings} from '../components/util/localization'
import {ACCESS_TOKEN, REFRESH_TOKEN, ROLE_ADMIN, ROLE_USER, SUCCESS, USER_ID} from '../constants'
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
import FlowersList from "../components/products/flower/FlowersList";
import ReviewsList from "../components/company/review/ReviewsList";
import BouquetList from "../components/products/bouquet/BouquetList";
import LegalPage from "../components/common/pages/LegalPage";
import DocumentsPage from "../components/common/pages/DocumentsPage";
import HelpPage from "../components/common/pages/HelpPage";
import AboutPage from "../components/common/pages/AboutPage";
import ShopDetail from "../components/shop/ShopDetail";
import Basket from "../components/basket/Basket";

const {Content} = Layout

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
            currentUser: null,

            currentCompany: null,

            isAuthenticated: false,
            isLoading: false,

        }

        notification.config({
            placement: "topRight",
            top: 70,
            duration: 2,
        })
    }

    loadCurrentCompany = () => {

        this.setState({
            isLoading: true
        })
        getCurrentCompanyRequest()
            .then(response => {
                console.log('RESPONSE currentCompany ' + response)
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
                console.log(response)

                localStorage.setItem(USER_ID, response.id)

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

            localStorage.removeItem(ACCESS_TOKEN)
            localStorage.removeItem(REFRESH_TOKEN)

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

        if (localStorage.getItem(ACCESS_TOKEN) && this.state.currentUser === undefined) {
            return <LoadingIndicator/>
        }

        //console.log('APP : this.state.currentUser ' + this.state.currentUser.id)

        return (
            <>
                <Layout className="app-wrapper">
                    <AppHeader isAuthenticated={this.state.isAuthenticated}
                               currentUser={this.state.currentUser}
                               handleLogout={this.handleLogout}
                    />


                    <Content className="app-content mb-5">
                        <div className="site-layout-background" style={{minHeight: 380}}>
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

                                <PrivateRoute path="/basket"
                                              isAuthenticated={this.state.isAuthenticated}
                                              currentUser={this.state.currentUser}
                                              component={Basket}/>

                                <Route exact path="/about/documents"
                                       component={DocumentsPage}/>

                                <Route path="/company/shops/:id"
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       component={ShopDetail}/>

                                <Route path="/company/shops"
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       component={ShopsList}/>

                                <Route path="/company"
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       component={Company}/>

                                <Route exact path="/about/legal"
                                       component={LegalPage}/>

                                <Route path="/company/about"
                                       component={AboutPage}/>

                                <Route path="/company"
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       component={Company}/>

                                <Route exact path="/about/help"
                                       component={HelpPage}/>

                                <Route path="/flowers"
                                       loadCurrentUser={this.loadCurrentUser}
                                       isAuthenticated={this.state.isAuthenticated}
                                       currentUser={this.state.currentUser}
                                       component={FlowersList}/>

                                <Route path="/bouquets"
                                       isAuthenticated={this.state.isAuthenticated}
                                       currentUser={this.state.currentUser}
                                       component={BouquetList}/>

                                <Route path="/reviews"
                                       currentUser={this.state.currentUser}
                                       component={ReviewsList}/>

                                <Route path="/"
                                       currentUser={this.state.currentUser}
                                       component={Home}/>

                                <Route component={NotFound}/>

                            </Switch>
                        </div>

                    </Content>
                    <AppFooter currentCompany={this.state.currentCompany}/>
                </Layout>

            </>
        )
    }
}

export function isAdmin(currentUser) {
    console.log('is admin method works')

    console.log(currentUser)

    if (currentUser !== null && currentUser !== undefined && currentUser.roles !== undefined) {

        console.log('currentUser email= ' + currentUser.email)

        const role = currentUser.roles.find(elem => elem.name === ROLE_ADMIN)

        console.log("it`s admin ")

        return role === undefined ? false : role.name === ROLE_ADMIN
    }

    console.log('it`s user')
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

