import React, {Component} from 'react'
import './App.css'
import PrivateRoute from './util/PrivateRoute'

import {Col, Layout, notification, Row} from 'antd'
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
import PrivateAdminRoute from "./util/PrivateAdminRoute";
import OrderPage from "../components/order/OrderPage";
import BreadCrumbComponent from "../components/common/breadcrumb/BreadCrumbComponent";

const {Content} = Layout

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
            currentUser: null,

            currentCompany: null,

            isAuthenticated: false,
            isLoading: true,

        }

        notification.config({
            placement: "topRight",
            top: 70,
            duration: 2,
        })
    }

    loadCurrentCompany = () => {

        getCurrentCompanyRequest()
            .then(response => {
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

                // localStorage.setItem(USER_ID, response.id)

                this.setState({
                    currentUser: response,
                    isAuthenticated: true
                })
            }).catch(() => {
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

        return (
            <Layout className="app-wrapper">
                <AppHeader isAuthenticated={this.state.isAuthenticated}
                           currentUser={this.state.currentUser}
                           handleLogout={this.handleLogout}
                />

                <Content className="app-content">

                    <div className="mb-5">
                        <Row justify="center">
                            <Col span={22}>
                                <BreadCrumbComponent properties={this.props}/>
                            </Col>
                        </Row>
                    </div>

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
                                      component={Profile}
                                      {...this.props}/>


                        <Route path="/orders/:id"
                               currentUser={this.state.currentUser}
                               component={OrderPage}/>


                        <PrivateRoute path="/basket"
                                      isAuthenticated={this.state.isAuthenticated}
                                      currentUser={this.state.currentUser}
                                      component={Basket}
                                      {...this.props}/>

                        <Route exact path="/about/documents"
                               render={(props) =>
                                   <DocumentsPage
                                       {...props} />}/>


                        <Route path="/company/shops/:id"
                               render={(props) =>
                                   <ShopDetail
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       {...props} />}/>

                        <Route path="/company/shops"
                               render={(props) =>
                                   <ShopsList
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       {...props} />}/>

                        <Route path="/company"
                               render={(props) =>
                                   <Company
                                       currentUser={this.state.currentUser}
                                       currentCompany={this.state.currentCompany}
                                       {...props} />}/>


                        <Route exact path="/about/legal"
                               render={(props) =>
                                   <LegalPage
                                       {...props} />}/>


                        <Route path="/company/about"
                               render={(props) =>
                                   <AboutPage
                                       {...props} />}/>


                        <PrivateAdminRoute path="/company"
                                           isAuthenticated={this.state.isAuthenticated}
                                           currentUser={this.state.currentUser}
                                           currentCompany={this.state.currentCompany}
                                           component={Company}/>

                        <Route exact path="/about/help"
                               render={(props) =>
                                   <HelpPage
                                       {...props} />}/>


                        <Route path="/flowers"
                               render={(props) =>
                                   <FlowersList
                                       loadCurrentUser={this.loadCurrentUser}
                                       isAuthenticated={this.state.isAuthenticated}
                                       currentUser={this.state.currentUser}
                                       {...props} />}/>

                        <Route path="/bouquets"
                               render={(props) =>
                                   <BouquetList
                                       isAuthenticated={this.state.isAuthenticated}
                                       currentUser={this.state.currentUser}
                                       {...props} />}/>


                        <Route path="/reviews"
                               render={(props) =>
                                   <ReviewsList
                                       currentUser={this.state.currentUser}
                                       {...props} />}/>


                        <Route path="/"
                               render={(props) =>
                                   <Home
                                       currentUser={this.state.currentUser}
                                       {...props} />}/>


                        <Route component={NotFound}/>

                    </Switch>
                </Content>
                <AppFooter currentCompany={this.state.currentCompany}/>
            </Layout>
        )
    }
}

export function isAdmin(currentUser) {

    if (currentUser !== null && currentUser !== undefined && currentUser.roles !== undefined) {
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
