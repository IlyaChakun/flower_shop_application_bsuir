import React, {Component} from 'react'
import {Link, withRouter} from 'react-router-dom'
import {Avatar, Button, Dropdown, Layout, Menu} from 'antd'
import {getAvatarColor} from '../../util/colors'

import {localizedStrings} from '../../util/localization'
import './AppHeader.css'

import HomeOutlined from '@ant-design/icons/lib/icons/HomeOutlined'
import UserOutlined from '@ant-design/icons/lib/icons/UserOutlined'
import CaretDownOutlined from '@ant-design/icons/lib/icons/CaretDownOutlined'
import LoginOutlined from "@ant-design/icons/lib/icons/LoginOutlined";
import UserAddOutlined from "@ant-design/icons/lib/icons/UserAddOutlined";
import {isAdmin} from "../../../app/App";

const Header = Layout.Header

class AppHeader extends Component {

    state = {
        language: this.props.language
    }

    handleMenuClick = ({key}) => {
        if (key === 'logout') {
            this.props.handleLogout()
        }
        if (key === 'profile') {
            this.props.history.push('/profile')
        }
    }

    makeMenuForUser = () => {
        return [
            <Menu.Item key="/">
                <Link to="/">
                    <HomeOutlined style={{fontSize: '20px'}}/>
                </Link>
            </Menu.Item>,

            <Menu.Item key="/profile" className="profile-menu">
                <ProfileDropdownMenu
                    currentUser={this.props.currentUser}
                    handleMenuClick={this.handleMenuClick}
                />
            </Menu.Item>
        ]
    }

    makeMenuForGuest = () => {

        return [
            <Menu.Item key="/sign-up">
                <Link to="/sign-up">
                    <UserAddOutlined style={{fontSize: '20px'}}/>
                </Link>
            </Menu.Item>,

            <Menu.Item key="/login">
                <Link to="/login">
                    <LoginOutlined style={{fontSize: '20px'}}/>
                </Link>
            </Menu.Item>
        ]
    }


    makeMenuForShopAdmin = () => {

        return [
            <Menu.Item key="/company" className="">
                <Link to="/company">
                    Компания
                </Link>
            </Menu.Item>,

            <Menu.Item key="/company/shops" className="">
                <Link to="/company/shops">
                    Магазины
                </Link>
            </Menu.Item>,

            <Menu.Item key="/profile"
                       className="profile-menu">
                <ProfileDropdownMenu
                    currentUser={this.props.currentUser}
                    handleMenuClick={this.handleMenuClick}
                />
            </Menu.Item>
        ]
    }

    updateLanguage = lang => {
        this.setState({
            language: lang
        })
        this.props.handleLanguageChange(lang)
    }

    render() {
        let menuItems

        if (this.props.currentUser) {
            menuItems = this.makeMenuForUser()
        } else {
            menuItems = this.makeMenuForGuest()
        }

        if (isAdmin(this.props.currentUser)) {
            menuItems = this.makeMenuForShopAdmin()
        }

        return (

            <>
                <div className="row">

                    <div className="col-4">

                    </div>

                    <div className="col-4">

                        <img alt="logo"
                             width="50%"
                             height="35%"
                             className="img-fluid"
                             src="https://atlanticcityflorist.com/wp-content/uploads/2019/10/logoacfstransparentbg.png"/>

                    </div>

                    <div className="col-4"></div>
                </div>

                {/*<Header style={{ position: 'fixed', zIndex: 1, width: '100%'}}>*/}
                <Header >
                    <Menu
                        theme={"dark"}
                        mode="horizontal"
                        selectedKeys={[this.props.location.pathname]}
                        style={{lineHeight: '60px'}}>
                        <Menu.Item key="/">
                            <Link to="/">
                                Главная страница
                            </Link>
                        </Menu.Item>
                        {menuItems}
                    </Menu>
                </Header>
            </>
        )
    };
}

function ProfileDropdownMenu(props) {
    const image = props.currentUser.imageUrl ? (
        <img src={props.currentUser.imageUrl} alt={props.currentUser.name}/>
    ) : (
        <div className="text-avatar">
            <span>{props.currentUser.name && props.currentUser.name[0]}</span>
        </div>
    )

    const dropdownMenu = (
        <Menu onClick={props.handleMenuClick} className="profile-dropdown-menu">
            <Menu.Item key="user-info"
                       className="dropdown-item"
                       disabled>
                <Avatar className="user-avatar-circle"
                        icon={image}
                        style={{backgroundColor: getAvatarColor(props.currentUser.name)}}>
                    {props.currentUser.name[0].toUpperCase()}
                </Avatar>
                <div className="user-full-name-info">
                    {props.currentUser.name}
                </div>
            </Menu.Item>
            <Menu.Divider/>
            <Menu.Item key="profile" className="dropdown-item">
                {localizedStrings.profile}
            </Menu.Item>
            <Menu.Item key="logout" className="dropdown-item">
                {localizedStrings.logout}
            </Menu.Item>
        </Menu>
    )


    return (
        <Dropdown
            overlay={dropdownMenu}
            trigger={['click']}
            getPopupContainer={() => document.getElementsByClassName('profile-menu')[0]}>

            <Button type="link" className="ant-dropdown-link" onClick={event => event.preventDefault()}>
                <UserOutlined style={{marginRight: 0, fontSize: '20px'}}/>
                <CaretDownOutlined/>
            </Button>
        </Dropdown>
    )
}

export default withRouter(AppHeader)
