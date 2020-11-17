import React from 'react'
import { Layout } from 'antd'
import { localizedStrings } from '../../util/localization'
// import './AppFooter.css'
import '../../../index.css'
import AboutUsComponent from './AboutUsComponent'

// const Footer = Layout.Footer

const AppFooter = () => {
  return (
    <footer id="footer">
      <div className="footer-v2">
        <div className="footer-inner light">
          <div className="footer_top">
            <div className="maxwidth-theme">
              <div className="row">
                <div className="col-md-2 col-sm-3">
                  {/* <div className="fourth_bottom_menu"> */}
                  {/*  <div className="bottom-menu second"> */}
                  {/*    <div className="items"> */}
                  {/*      <div className="item"> */}
                  {/*        <div className="title"> */}
                  {/*          <a href="/blog/">Блог</a> */}
                  {/*        </div> */}
                  {/*      </div> */}
                  {/*    </div> */}
                  {/*  </div> */}
                  {/* </div> */}
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
                          <div className="compact_arrow">
                            <i className="svg  svg-inline-down colored_theme_hover_bg-el"
                              aria-hidden="true">
                              <svg xmlns="http://www.w3.org/2000/svg" width="8" height="5"
                                viewBox="0 0 8 5">
                                <path data-name="Rounded Rectangle 890 copy 2"
                                  className="cls-1"
                                  d="M517.778,610.8a0.721,0.721,0,0,1-1.016,0L514,607.769l-2.79,3.028a0.715,0.715,0,1,1-1.01-1.011l3.273-3.552c0.009-.009.012-0.021,0.021-0.03a0.723,0.723,0,0,1,1.017,0,0.022,0.022,0,0,1,0,0l3.265,3.577A0.712,0.712,0,0,1,517.778,610.8Z"
                                  transform="translate(-510 -606)"></path>
                              </svg>
                            </i></div>
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
                          <div className="compact_arrow">
                            <i className="svg  svg-inline-down colored_theme_hover_bg-el"
                              aria-hidden="true">
                              <svg xmlns="http://www.w3.org/2000/svg" width="8" height="5"
                                viewBox="0 0 8 5">
                                <path data-name="Rounded Rectangle 890 copy 2"
                                  className="cls-1"
                                  d="M517.778,610.8a0.721,0.721,0,0,1-1.016,0L514,607.769l-2.79,3.028a0.715,0.715,0,1,1-1.01-1.011l3.273-3.552c0.009-.009.012-0.021,0.021-0.03a0.723,0.723,0,0,1,1.017,0,0.022,0.022,0,0,1,0,0l3.265,3.577A0.712,0.712,0,0,1,517.778,610.8Z"
                                  transform="translate(-510 -606)"></path>
                              </svg>
                            </i></div>
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
                          <div className="compact_arrow">
                            <i className="svg  svg-inline-down colored_theme_hover_bg-el"
                              aria-hidden="true">
                              <svg xmlns="http://www.w3.org/2000/svg" width="8" height="5"
                                viewBox="0 0 8 5">
                                <path data-name="Rounded Rectangle 890 copy 2"
                                  className="cls-1"
                                  d="M517.778,610.8a0.721,0.721,0,0,1-1.016,0L514,607.769l-2.79,3.028a0.715,0.715,0,1,1-1.01-1.011l3.273-3.552c0.009-.009.012-0.021,0.021-0.03a0.723,0.723,0,0,1,1.017,0,0.022,0.022,0,0,1,0,0l3.265,3.577A0.712,0.712,0,0,1,517.778,610.8Z"
                                  transform="translate(-510 -606)"></path>
                              </svg>
                            </i></div>
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
                            data-name="subscribe">Подписаться на рассылку<i
                              className="svg inline  svg-inline-subscribe" aria-hidden="true"><svg
                                xmlns="http://www.w3.org/2000/svg" width="21" height="16"
                                viewBox="0 0 21 16"><path data-name="Rounded Rectangle 873 copy 3"
                                  className="cls-1"
                                  d="M286,133.856a0.949,0.949,0,0,1,0,.226c0,0.006,0,.013,0,0.02a0.955,0.955,0,0,1-.112.357l-4.958,10.883a0.927,0.927,0,0,1-.046.153,0.974,0.974,0,0,1-.179.214h0a0.974,0.974,0,0,1-.28.2c-0.017.008-.032,0.019-0.05,0.026a0.972,0.972,0,0,1-.267.063,1.07,1.07,0,0,1-.127.012,0.989,0.989,0,0,1-.213-0.036,1.01,1.01,0,0,1-.132-0.022c-0.021-.009-0.035-0.027-0.056-0.036s-0.036-.007-0.052-0.016l-3.133-1.8-3.523,4.4a22.232,22.232,0,0,1-.2.229,0.96,0.96,0,0,1-.218.147,1.048,1.048,0,0,1-.1.047,0.975,0.975,0,0,1-.3.06c-0.019,0-.035.011-0.054,0.011s-0.017,0-.026,0a0.968,0.968,0,0,1-.293-0.06,0.821,0.821,0,0,1-.1-0.033,0.792,0.792,0,0,1-.082-0.028,0.906,0.906,0,0,1-.131-0.116,0.73,0.73,0,0,1-.233-0.284,0.887,0.887,0,0,1-.069-0.142,1.032,1.032,0,0,1-.043-0.213A0.964,0.964,0,0,1,271,148v-6.986l-5.32-3.05a0.979,0.979,0,0,1-.618-0.621,1.19,1.19,0,0,1-.044-0.118c0-.014-0.012-0.024-0.014-0.039a0.749,0.749,0,0,1,0-.082,0.966,0.966,0,0,1,1.118-1.112L284.851,133c0.027,0,.052.005,0.078,0s0.047,0,.071,0a0.98,0.98,0,0,1,.307.037,0.906,0.906,0,0,1,.089.042,0.96,0.96,0,0,1,.233.128,1.008,1.008,0,0,1,.122.13,0.957,0.957,0,0,1,.119.15c0.011,0.018.029,0.029,0.039,0.048a1,1,0,0,1,.059.212,0.661,0.661,0,0,1,.028.074A0.226,0.226,0,0,1,286,133.856Zm-6.453,9.739,3.332-7.314-8.3,4.467ZM273,145.17l1.651-2.064L273,142.16v3.01Zm-0.462-5.59,6.83-3.676-10.359,1.653Z"
                                  transform="translate(-265 -133)"></path></svg></i></span>
                        </div>
                      </div>
                      <div className="col-md-12 col-sm-12">
                        <div className="phone blocks">
                          <div className="inline-block">
                            <div className="phone white sm">
                              <div className="wrap">
                                <div>
                                  <i className="svg inline  svg-inline-phone"
                                    aria-hidden="true">
                                    <svg xmlns="http://www.w3.org/2000/svg"
                                      width="5"
                                      height="11" viewBox="0 0 5 11">
                                      <path data-name="Shape 51 copy 13"
                                        className="cls-1"
                                        d="M402.738,141a18.086,18.086,0,0,0,1.136,1.727,0.474,0.474,0,0,1-.144.735l-0.3.257a1,1,0,0,1-.805.279,4.641,4.641,0,0,1-1.491-.232,4.228,4.228,0,0,1-1.9-3.1,9.614,9.614,0,0,1,.025-4.3,4.335,4.335,0,0,1,1.934-3.118,4.707,4.707,0,0,1,1.493-.244,0.974,0.974,0,0,1,.8.272l0.3,0.255a0.481,0.481,0,0,1,.113.739c-0.454.677-.788,1.159-1.132,1.731a0.43,0.43,0,0,1-.557.181l-0.468-.061a0.553,0.553,0,0,0-.7.309,6.205,6.205,0,0,0-.395,2.079,6.128,6.128,0,0,0,.372,2.076,0.541,0.541,0,0,0,.7.3l0.468-.063a0.432,0.432,0,0,1,.555.175h0Z"
                                        transform="translate(-399 -133)"></path>
                                    </svg>
                                  </i> <a rel="nofollow" href="tel:+375291456777">+375
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

                    {/* {localizedStrings.footerText} */}
                  </div>
                  <div id="bx-composite-banner" className="pull-left"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>

  )
}

export default AppFooter
