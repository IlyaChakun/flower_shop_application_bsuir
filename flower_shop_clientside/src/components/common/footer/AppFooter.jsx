import React, {Component} from 'react'
import {Col, Layout, Row} from 'antd'
import './AppFooter.css'
import {Link, withRouter} from 'react-router-dom'
import LoadingIndicator from "../util/LoadingIndicator";

const Footer = Layout.Footer

class AppFooter extends Component {

    state = {

        currentCompany: this.props.currentCompany,
        name: ' ',
        description: ' ',
        licenceNumber: ' ',
        firstPhoneNumber: ' ',
        secondPhoneNumber: ' ',
        email: ' ',
        city: ' ',
        address: ' ',
        payerAccountNumber: ' ',
        checkingAccount: ' ',
        bankName: ' ',
        bankCode: ' ',
        postalCode: ' ',
        bankAddress: ' ',

    }


    render() {


        if (!this.props.currentCompany) {
            return <LoadingIndicator/>
        }

        this.state.name = this.props.currentCompany.name
        this.state.description = this.props.currentCompany.description
        this.state.licenceNumber = this.props.currentCompany.licenceNumber
        this.state.firstPhoneNumber = this.props.currentCompany.contacts.firstPhoneNumber
        this.state.secondPhoneNumber = this.props.currentCompany.contacts.secondPhoneNumber
        this.state.email = this.props.currentCompany.contacts.email
        this.state.city = this.props.currentCompany.contacts.city
        this.state.address = this.props.currentCompany.contacts.address
        this.state.payerAccountNumber = this.props.currentCompany.companyLegalAddress.payerAccountNumber
        this.state.checkingAccount = this.props.currentCompany.companyLegalAddress.checkingAccount
        this.state.bankName = this.props.currentCompany.companyLegalAddress.bankInformation.bankName
        this.state.bankCode = this.props.currentCompany.companyLegalAddress.bankInformation.bankCode
        this.state.postalCode = this.props.currentCompany.companyLegalAddress.bankInformation.postalCode
        this.state.bankAddress = this.props.currentCompany.companyLegalAddress.bankInformation.address

        return (
            <Footer>
                <div className="footer-top pb-5">
                    <Row>
                        <Col span={4} offset={4}>
                            <div className="items">
                                <div className="item">
                                    <a href="/about/">О нас</a>
                                </div>
                                <div className="item">
                                    <Link to="/company/about">О компании</Link>
                                </div>
                                <div className="item">
                                    <Link to="/about/help">Как оформить заказ</Link>
                                </div>
                                <div className="item">
                                    <Link to="/company/reviews">Отзывы</Link>
                                </div>
                                <div className="item">
                                    <Link to="/company/shops">Магазины</Link>
                                </div>
                                <div className="item">
                                    <Link to="/about/legal">Юридическим лицам</Link>
                                </div>
                                <div className="item">
                                    <Link to="/about/documents">Документы</Link>
                                </div>
                            </div>
                        </Col>
                        <Col span={6}>
                            <div className="items">
                                <div className="item">
                                    <a href="/info/oferta/">Информация</a>
                                </div>
                                <div className="item">
                                    <a href="/info/requisites/">Реквизиты</a>
                                </div>
                                <div className="item">
                                    <a href="/include/licenses_detail.php">
                                        Политика конфиденциальности</a>
                                </div>
                                <div className="item">
                                    <a href="/info/oferta/">Договор публичной оферты</a>
                                </div>
                                <div className="item">
                                    <a href="/contacts/">Контакты</a>
                                </div>
                            </div>
                        </Col>
                        <Col span={4}>
                            <div className="items">
                                <div className="item">
                                    <a href="/about/help/">Как купить</a>
                                </div>
                                <div className="item ">
                                    <a href="/about/delivery/">Доставка</a>
                                </div>
                                <div className="item">
                                    <a href="/about/payment/">Оплата</a>
                                </div>
                                <div className="item">
                                    <a href="/about/warranty/">Гарантии</a>
                                </div>
                            </div>
                        </Col>
                        <Col span={6}>
                            <div className="contact-block">
                                <div className="phone mt-4">
                                    <Row justify="center">
                                        <Col span={12}>
                                            <a rel="nofollow" href="tel:+375291456777">
                                                {this.state.firstPhoneNumber}
                                            </a>
                                        </Col>
                                    </Row>
                                </div>
                                <div className="email">
                                    <Row justify="center">
                                        <Col span={12}>
                                            <a href="mailto:info@donnarosa.by"
                                               target="_blank"
                                               rel="noopener noreferrer"
                                            >
                                                {this.state.email}
                                            </a>
                                        </Col>

                                    </Row>
                                </div>
                                <div className="address ">
                                    <Row justify="center">
                                        <Col span={12}>
                                            {this.state.city}, {this.state.address}
                                        </Col>
                                    </Row>
                                </div>
                            </div>
                        </Col>
                    </Row>
                </div>
                <div className="footer-middle">
                    <Row>
                        <Col span={24}>
                            <div className="social-block">
                                <ul className="list-group list-group-horizontal justify-content-center pb-2">
                                    <li className="vk">
                                        <a href="#" target="_blank" rel="nofollow" title="Вконтакте">
                                            <i className="fa fa-vk" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                    <li className="facebook">
                                        <a href="#" target="_blank" rel="nofollow" title="Facebook">
                                            <i className="fa fa-facebook"></i>
                                        </a>
                                    </li>
                                    <li className="instagram">
                                        <a href="#" target="_blank" rel="nofollow" title="Instagram">
                                            <i className="fa fa-instagram" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                    <li className="telegram">
                                        <a href="#" target="_blank" rel="nofollow" title="Telegram">
                                            <i className="fa fa-telegram" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </Col>
                    </Row>
                </div>
                <div className="footer_bottom pt-5">
                    <Row justify="center">
                        <Col>
                            <div className="copy">
                                2020 © {this.state.name} УНП № {this.state.checkingAccount}<br/>
                                Интернет-магазин зарегистрирован в торговом реестре 30.12.2019 под
                                номером {this.state.licenceNumber}<br/>
                                Адрес: {this.state.city},{this.state.address}, E-mail: {this.state.email}, <br/>
                                Тел.: {this.state.firstPhoneNumber}, Доп. тел.: {this.state.secondPhoneNumber}<br/>
                                Р/с: {this.state.payerAccountNumber} в {this.state.bankName},
                                {this.state.postalCode}, {this.state.bankAddress}, код банка: {this.state.bankCode}
                            </div>
                        </Col>
                    </Row>
                </div>
            </Footer>
        )
    }
}

export default withRouter(AppFooter)
