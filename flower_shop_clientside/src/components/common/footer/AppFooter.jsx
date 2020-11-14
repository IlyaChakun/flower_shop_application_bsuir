import React from 'react'
import {Layout} from 'antd'
import {localizedStrings} from '../../util/localization'
// import './AppFooter.css'
import '../../../index.css'


const Footer = Layout.Footer

const AppFooter = () => {
    return (
        // <Footer className='footer'>
        <footer id="footer">
            <div className="footer-inner light">
                <div className="footer_top">
                    <div className="maxwidth-theme">

                        <div className="row">



                            <div className="col-md-3 col-sm-3">
                                <div className="first_bottom_menu">
                                    <div className="bottom-menu">
                                        <div className="items">
                                            <div className="item 0 childs   accordion-close ">
                                                <div className="title">
                                                    <a> О нас</a>
                                                </div>
                                            </div>

                                            <div id="bottom_help"
                                                 className="wrap panel-collapse wrap_compact_mobile">
                                                <div className="item">
                                                    <div className="title">
                                                        <a href="">О компании</a>
                                                    </div>
                                                </div>
                                                <div className="item">
                                                    <div className="title">
                                                        <a href="">Как оформить заказ</a>
                                                    </div>
                                                </div>
                                                <div className="item">
                                                    <div className="title">
                                                        <a href="">Отзывы</a>
                                                    </div>
                                                </div>
                                                <div className="item active">
                                                    <div className="title">
                                                        <a href="">Магазины</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-3 col-sm-3">
                                <div className="second_bottom_menu">
                                    <div className="bottom-menu">
                                        <div className="items">
                                            <div className="item 0 childs   accordion-close ">
                                                <div className="title">
                                                    <a>Информация</a>
                                                </div>

                                                <div id="bottom_help"
                                                     className="wrap panel-collapse wrap_compact_mobile">
                                                    <div className="item 1    ">
                                                        <div className="title">
                                                            <a href="">Реквизиты</a>
                                                        </div>
                                                    </div>
                                                    <div className="item 4   active  ">
                                                        <div className="title">
                                                            <a href="">Контакты</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-3 col-sm-3">
                                <div className="third_bottom_menu">
                                    <div className="bottom-menu">
                                        <div className="items">
                                            <div className="item 0 childs   accordion-close ">
                                                <div className="title">
                                                    <a>Как купить</a>
                                                </div>
                                                <div id="bottom_help"
                                                     className="wrap panel-collapse wrap_compact_mobile">
                                                    <div className="item 1">
                                                        <div className="title">
                                                            <a href="">Доставка</a>
                                                        </div>
                                                    </div>
                                                    <div className="item 2">
                                                        <div className="title">
                                                            <a href="">Оплата</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-3 col-sm-12 contact-block">
                                <div className="info">
                                    <div className="row">

                                        <div className="col-md-12 col-sm-12">
                                            <div className="phone blocks">
                                                <div className="inline-block">
                                                    <div className="phone white sm">
                                                        <div className="wrap">
                                                            <div>
                                                                <i className="svg inline  svg-inline-phone"
                                                                   aria-hidden="true">
                                                                    <svg xmlns="http://www.w3.org/2000/svg" width="5"
                                                                         height="11" viewBox="0 0 5 11">
                                                                        <path data-name="Shape 51 copy 13"
                                                                              className="cls-1"
                                                                              d="M402.738,141a18.086,18.086,0,0,0,1.136,1.727,0.474,0.474,0,0,1-.144.735l-0.3.257a1,1,0,0,1-.805.279,4.641,4.641,0,0,1-1.491-.232,4.228,4.228,0,0,1-1.9-3.1,9.614,9.614,0,0,1,.025-4.3,4.335,4.335,0,0,1,1.934-3.118,4.707,4.707,0,0,1,1.493-.244,0.974,0.974,0,0,1,.8.272l0.3,0.255a0.481,0.481,0,0,1,.113.739c-0.454.677-.788,1.159-1.132,1.731a0.43,0.43,0,0,1-.557.181l-0.468-.061a0.553,0.553,0,0,0-.7.309,6.205,6.205,0,0,0-.395,2.079,6.128,6.128,0,0,0,.372,2.076,0.541,0.541,0,0,0,.7.3l0.468-.063a0.432,0.432,0,0,1,.555.175h0Z"
                                                                              transform="translate(-399 -133)"></path>
                                                                    </svg>
                                                                </i> <a rel="nofollow" href="tel:+375291456777">+375
                                                                (29)
                                                                1-456-777</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div className="col-md-12 col-sm-12">
                                            <div className="email blocks">
                                                <i className="svg inline  svg-inline-email" aria-hidden="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="11" height="9"
                                                         viewBox="0 0 11 9">
                                                        <path data-name="Rectangle 583 copy 16" className="cls-1"
                                                              d="M367,142h-7a2,2,0,0,1-2-2v-5a2,2,0,0,1,2-2h7a2,2,0,0,1,2,2v5A2,2,0,0,1,367,142Zm0-2v-3.039L364,139h-1l-3-2.036V140h7Zm-6.634-5,3.145,2.079L366.634,135h-6.268Z"
                                                              transform="translate(-358 -133)"></path>
                                                    </svg>
                                                </i> <a href="mailto:info@donnarosa.by"
                                                        target="_blank">info@donnarosa.by</a>
                                            </div>
                                        </div>
                                        <div className="col-md-12 col-sm-12">
                                            <div className="address blocks">
                                                <i className="svg inline  svg-inline-addr" aria-hidden="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="9" height="12"
                                                         viewBox="0 0 9 12">
                                                        <path className="cls-1"
                                                              d="M959.135,82.315l0.015,0.028L955.5,87l-3.679-4.717,0.008-.013a4.658,4.658,0,0,1-.83-2.655,4.5,4.5,0,1,1,9,0A4.658,4.658,0,0,1,959.135,82.315ZM955.5,77a2.5,2.5,0,0,0-2.5,2.5,2.467,2.467,0,0,0,.326,1.212l-0.014.022,2.181,3.336,2.034-3.117c0.033-.046.063-0.094,0.093-0.142l0.066-.1-0.007-.009a2.468,2.468,0,0,0,.32-1.2A2.5,2.5,0,0,0,955.5,77Z"
                                                              transform="translate(-951 -75)"></path>
                                                    </svg>
                                                </i> г. Минск, ул. Сырокомли 38
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                {/*<div className="footer_middle">*/}
                {/*    <div className="maxwidth-theme">*/}
                {/*        <div className="row">*/}
                {/*            <div className="col-md-12 col-sm-12">*/}
                {/*                <div className="social-block">*/}
                {/*                    <div className="social-icons">*/}
                {/*                        <ul>*/}
                {/*                            <li className="vk">*/}
                {/*                                <a href="https://vk.com/donnarosaminsk" target="_blank" rel="nofollow"*/}
                {/*                                   title="Вконтакте">*/}
                {/*                                    Вконтакте </a>*/}
                {/*                            </li>*/}
                {/*                            <li className="facebook">*/}
                {/*                                <a href="https://www.facebook.com/donnarosa.by/?view_public_for=272025159873121"*/}
                {/*                                   target="_blank" rel="nofollow" title="Facebook">*/}
                {/*                                    Facebook </a>*/}
                {/*                            </li>*/}
                {/*                            <li className="instagram">*/}
                {/*                                <a href="https://www.instagram.com/donnarosa.by/" target="_blank"*/}
                {/*                                   rel="nofollow" title="Instagram">*/}
                {/*                                    Instagram </a>*/}
                {/*                            </li>*/}
                {/*                            <li className="telegram">*/}
                {/*                                <a href="https://t.me/donnarosa_by" target="_blank" rel="nofollow"*/}
                {/*                                   title="Telegram">*/}
                {/*                                    Telegram </a>*/}
                {/*                            </li>*/}
                {/*                        </ul>*/}
                {/*                    </div>*/}
                {/*                </div>*/}
                {/*            </div>*/}
                {/*        </div>*/}
                {/*    </div>*/}
                {/*</div>*/}

                <div className="footer_bottom">
                    <div className="maxwidth-theme">
                        <div className="row">
                            <div className="copy-block col-md-6 col-sm-6 pull-left">
                                <div className="copy font_xs pull-left">
                                    {localizedStrings.footerText}
                                </div>

                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </footer>

        /*</Footer>*/

    )
}

export default AppFooter
