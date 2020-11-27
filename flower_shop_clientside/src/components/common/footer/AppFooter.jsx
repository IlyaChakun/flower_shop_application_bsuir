import React, {Component} from 'react'
import {Layout} from 'antd'
import './AppFooter.css'
import {Link, withRouter} from 'react-router-dom'
import LoadingIndicator from "../util/LoadingIndicator";

const Footer = Layout.Footer

class AppFooter extends Component {

    state = {

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


        console.log('FOOTER ' + this.state.firstPhoneNumber)

        return (
            <Footer style={{textAlign: 'center'}}>
                <div className="footer">
                    <div className="row footer_top pb-5">
                        <div className="col-md-2 col-sm-3">
                        </div>
                        <div className="col-md-2 col-sm-3">
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
                                <div className="item"><Link to="/about/documents">Документы</Link>
                                </div>
                            </div>

                        </div>
                        <div className="col-md-3 col-sm-3">
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
                        </div>
                        <div className="col-md-2 col-sm-3">
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
                        </div>
                        <div className="col-md-3 col-sm-12 contact-block">
                            <div className="row phone mt-4">
                                <div className="col-6 mx-auto">
                                    <a rel="nofollow" href="tel:+375291456777">
                                        {this.state.firstPhoneNumber}
                                    </a>
                                </div>

                            </div>
                            <div className="row email">
                                <div className="col-6  mx-auto">
                                    <a href="mailto:info@donnarosa.by"
                                       target="_blank"
                                       rel="noopener noreferrer"
                                    >
                                        {this.state.email}
                                    </a>
                                </div>

                            </div>
                            <div className="row address ">
                                <div className="col-6  mx-auto">
                                    {this.state.city}
                                    {this.state.address}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row footer_middle">
                        <div className="col-md-12 col-sm-12">
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
                        </div>
                    </div>
                    <div className="row footer_bottom pt-5">
                        <div className="col-md-6 col-sm-6 mx-auto">
                            <div className="copy">
                                2020 © {this.state.name} УНП № {this.state.checkingAccount}<br/>
                                Интернет-магазин зарегистрирован в торговом реестре 30.12.2019 под
                                номером {this.state.licenceNumber}<br/>
                                Адрес: {this.state.city},{this.state.address}, E-mail: {this.state.email}, <br/>
                                Тел.: {this.state.firstPhoneNumber}, Доп. тел.: {this.state.secondPhoneNumber}<br/>
                                Р/с: {this.state.payerAccountNumber} в {this.state.bankName},
                                {this.state.postalCode}, {this.state.bankAddress}, код банка: {this.state.bankCode}
                            </div>
                        </div>
                    </div>
                </div>
            </Footer>
        )
    }
}

export default withRouter(AppFooter)
