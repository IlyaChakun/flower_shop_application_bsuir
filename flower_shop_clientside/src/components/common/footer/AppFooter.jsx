import React from 'react'
import {Layout, Menu} from 'antd'
import './AppFooter.css'
import {Link, NavLink} from 'react-router-dom'

const Footer = Layout.Footer

const AppFooter = () => {
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
                                <a rel="nofollow" href="tel:+375291456777">+375
                                    (29) 1-456-777</a>
                            </div>

                        </div>
                        <div className="row email">
                            <div className="col-6  mx-auto">
                                <a href="mailto:info@donnarosa.by"
                                   target="_blank">info@donnarosa.by</a>
                            </div>

                        </div>
                        <div className="row address ">
                            <div className="col-6  mx-auto">
                                г. Минск, ул. Сырокомли 38
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
                            2020 © ЗАО "Андрусерра Агро" УНП № 690557753<br/>
                            Интернет-магазин зарегистрирован в торговом реестре 30.12.2019 под номером
                            469671
                        </div>
                    </div>
                </div>
            </div>
        </Footer>
    )
}
export default AppFooter
