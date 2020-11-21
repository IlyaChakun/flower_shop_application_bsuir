import React from 'react'
import { Layout } from 'antd'
import { localizedStrings } from '../../util/localization'
// import './AppFooter.css'
import '../../../index.css'
import AboutUsComponent from './AboutUsComponent'

const Footer = Layout.Footer

const AppFooter = () => {
  return (

      <Footer style={{ textAlign: 'center' }}>

    {/*<footer id="footer">*/}
      <div className="footer-v2">
        <div className="footer-inner light">
          <div className="footer_top">
            <div className="maxwidth-theme">
              <div className="row">
                <div className="col-md-2 col-sm-3">

                </div>
                <div className="col-md-2 col-sm-3">
                  <div className="first_bottom_menu">
                    <div className="bottom-menu">
                      <div className="items">
                        <div className="item 0 childs   accordion-close "
                          data-parent="#bottom_company" data-target="#bottom_company">
                          <div className="title">
                            <a href="/about/">О нас</a>
                          </div>

                        </div>
                        <div id="bottom_company"
                          className="wrap panel-collapse wrap_compact_mobile">
                          <div className="item">
                            <div className="title">
                              <a href="/about/index.php">О компании</a>
                            </div>
                          </div>
                          <div className="item">
                            <div className="title">
                              <a href="/about/help/">Как оформить заказ</a>
                            </div>
                          </div>
                          <div className="item">
                            <div className="title">
                              <a href="/about/reviews/">Отзывы</a>
                            </div>
                          </div>
                          <div className="item">
                            <div className="title">
                              <a href="/contacts/stores/">Магазины</a>
                            </div>
                          </div>
                          <div className="item">
                            <div className="title">
                              <a href="/about/legal/">Юридическим лицам</a>
                            </div>
                          </div>
                          <div className="item">
                            <div className="title">
                              <a href="/about/licenses/">Документы</a>
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
                        <div className="item 0 childs   accordion-close "
                          data-parent="#bottom_info"
                          data-target="#bottom_info">
                          <div className="title">
                            <a href="/info/oferta/">Информация</a>
                          </div>

                        </div>
                        <div id="bottom_info"
                          className="wrap panel-collapse wrap_compact_mobile">
                          <div className="item 1    ">
                            <div className="title">
                              <a href="/info/requisites/">Реквизиты</a>
                            </div>
                          </div>
                          <div className="item 2    ">
                            <div className="title">
                              <a href="/include/licenses_detail.php">Политика
                                                                конфиденциальности</a>
                            </div>
                          </div>
                          <div className="item 3    ">
                            <div className="title">
                              <a href="/info/oferta/">Договор публичной оферты</a>
                            </div>
                          </div>
                          <div className="item 4    ">
                            <div className="title">
                              <a href="/contacts/">Контакты</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col-md-2 col-sm-3">
                  <div className="third_bottom_menu">
                    <div className="bottom-menu">
                      <div className="items">
                        <div className="item 0 childs   accordion-close "
                          data-parent="#bottom_help"
                          data-target="#bottom_help">
                          <div className="title">
                            <a href="/about/help/">Как купить</a>
                          </div>

                        </div>
                        <div id="bottom_help"
                          className="wrap panel-collapse wrap_compact_mobile">
                          <div className="item 1    ">
                            <div className="title">
                              <a href="/about/delivery/">Доставка</a>
                            </div>
                          </div>
                          <div className="item 2    ">
                            <div className="title">
                              <a href="/about/payment/">Оплата</a>
                            </div>
                          </div>
                          <div className="item 3    ">
                            <div className="title">
                              <a href="/about/warranty/">Гарантии</a>
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
                        <div className="subscribe_button">
                          <span className="btn has-ripple" data-event="jqm"
                            data-param-id="subscribe" data-param-type="subscribe"
                            data-name="subscribe">Подписаться на рассылку</span>
                        </div>
                      </div>
                      <div className="col-md-12 col-sm-12">
                        <div className="phone blocks">
                          <div className="inline-block">
                            <div className="phone white sm">
                              <div className="wrap">
                                <div>
                                  <a rel="nofollow" href="tel:+375291456777">+375
                                                                        (29) 1-456-777</a>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div className="inline-block callback_wrap">
                            <span className="callback-block animate-load colored"
                              data-event="jqm" data-param-form_id="CALLBACK"
                              data-name="callback">Заказать звонок</span>
                          </div>
                        </div>
                      </div>
                      <div className="col-md-12 col-sm-12">
                        <div className="email blocks">
                          <a href="mailto:info@donnarosa.by"
                            target="_blank">info@donnarosa.by</a>
                        </div>
                      </div>
                      <div className="col-md-12 col-sm-12">
                        <div className="address blocks">
                                                  г. Минск, ул. Сырокомли 38
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="footer_middle">
            <div className="maxwidth-theme">
              <div className="row">
                <div className="col-md-12 col-sm-12">
                  <div className="social-block">
                    <div className="social-icons">
                      <ul>
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
              </div>
            </div>
          </div>
          <div className="footer_bottom">
            <div className="maxwidth-theme">
              <div className="row">
                <div className="link_block col-md-6 col-sm-6 pull-right">
                  <div className="pull-right">
                    <div className="pays">
                      <i title="Оплата наличными" className="cacsh"></i>
                      <i title="Visa" className="visa"></i>
                      <i title="MasterCard" className="mastercard"></i></div>
                  </div>
                  <div className="pull-right">
                  </div>
                </div>
                <div className="copy-block col-md-6 col-sm-6 pull-left">
                  <div className="copy font_xs pull-left">
                                        2020 © ЗАО "Андрусерра Агро" УНП № 690557753<br/>
                                        Интернет-магазин зарегистрирован в торговом реестре 30.12.2019 под номером
                                        469671

                  </div>
                  <div id="bx-composite-banner" className="pull-left"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    {/*</footer>*/}
      </Footer>
  )
}

export default AppFooter
