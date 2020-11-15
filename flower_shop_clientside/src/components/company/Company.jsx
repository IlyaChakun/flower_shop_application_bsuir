import React, {Component} from 'react'
import {localizedStrings} from '../util/localization'
import {Button, Form, Input, notification} from "antd";

import CompanyForm from "../company/CompanyForm";

import "../../index.css";

class Company extends Component {

    render() {
        return (
            <div className="container-fluid">

                <div className="wrapper_inner  ">
                    <div className="wraps hover_shine " id="content">

                        <div className="top-block-wrapper title_position_CENTERED">
                            <section className="page-top maxwidth-theme ">
                                <div className="topic">
                                    <div className="topic__inner">
                                        <div className="topic__heading">
                                            <h1 id="pagetitle">{localizedStrings.aboutCompany}</h1>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>

                        <div className="row m-0 p-0">
                            <div className="col m-0 p-0">
                                <div className="left_block sticky-sidebar">
                                    <div className="sticky-sidebar__inner">

                                        <div className="menu_top_block menu-type1">

                                            <ul className="left_menu dropdown">

                                                <li className="v_bottom item    item ">
                                                    <a href="">
                                                        <span className="name">Как оформить заказ</span>
                                                    </a>
                                                </li>
                                                <li className="v_bottom item    item ">
                                                    <a  href="">
                                                        <span className="name">Отзывы</span>
                                                    </a>
                                                </li>
                                                <li className="v_bottom item   item ">
                                                    <a  href="">
                                                        <span className="name">{localizedStrings.companyShops}</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>


                                        <div
                                            className="side-block bordered box-shadow rounded2 colored_theme_hover_bg-block">
                                            <div className="side-block__top text-center">
                                                <i className="svg  svg-inline-icon colored" aria-hidden="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="65.03" height="56"
                                                         viewBox="0 0 65.03 56">
                                                        <path className="cls-1 svg-opacity-01"
                                                              d="M1486,357l48-18,5-1-38,24Zm53-16-11.16,31.481-18-7L1540,338Z"
                                                              transform="translate(-1478.97 -330)"></path>
                                                        <path data-name="Rounded Rectangle 1047" className="cls-2s"
                                                              d="M1543.94,335.353c-0.01.008,0,.017-0.01,0.025v0.01l-0.03.07-14.95,37.907a0.991,0.991,0,0,1-1.13.591,0.935,0.935,0,0,1-.18-0.028l-13.28-4.882-10.62,10.614s-0.01.012-.01,0.017a0.041,0.041,0,0,0-.02.019h0c-0.04.037-.09,0.07-0.13,0.1a1.39,1.39,0,0,1-.17.1l-0.01,0a1.063,1.063,0,0,1-.13.038l-0.03.007a1.062,1.062,0,0,1-.15.044,0.214,0.214,0,0,1-.05-0.006,0.936,0.936,0,0,1-1-.837c-0.01-.026-0.02-0.05-0.03-0.078l-1.97-16.321-16.24-4.785a0.946,0.946,0,0,1-.41-0.2,0.191,0.191,0,0,1-.05-0.037,1.144,1.144,0,0,1-.15-0.174,1.156,1.156,0,0,1-.1-0.179c-0.01-.015-0.02-0.025-0.03-0.041s0-.05-0.01-0.075a0.9,0.9,0,0,1-.04-0.283c0-.037.01-0.072,0.01-0.108a1.02,1.02,0,0,1,.02-0.173c0.01-.032.03-0.054,0.04-0.084a0.829,0.829,0,0,1,.08-0.146c0.04-.054.07-0.107,0.11-0.153a0.965,0.965,0,0,1,.21-0.167c0.02-.014.05-0.03,0.07-0.042,0.04-.021.07-0.053,0.11-0.069l59.01-21.981a1,1,0,0,1,1.28.607,1.214,1.214,0,0,1,.04.266c0,0.025.01,0.048,0.01,0.074A1.047,1.047,0,0,1,1543.94,335.353Zm-31.64,32.936-2.8-1.027-3.24,7.062Zm-25.13-11.4,13.72,4.045,33.12-21.493Zm14.86,5.691,1.55,12.791,4.45-9.689a1,1,0,0,1,.26-0.408l27.88-24.848Zm8.86,3.061,16.55,6.085,12.69-32.152Zm-3.25-27.912a0.984,0.984,0,0,1-1.4-.053,1,1,0,0,1,.05-1.413l6.02-6.021a1,1,0,0,1,1.36,1.466Zm-17.89,31.57a1,1,0,0,1-.08,1.412l-9.02,8.041a1,1,0,0,1-1.33-1.5l9.02-8.04A1,1,0,0,1,1489.75,369.3Zm26.6,11.918a1,1,0,1,1,1.29,1.526l-3,3.015a1,1,0,1,1-1.29-1.526Z"
                                                              transform="translate(-1478.97 -330)"></path>
                                                    </svg>
                                                </i>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div className="col-8 m-0 p-0">
                                <div className="wrapper1 ">

                                    <div className="container_inner clearfix ">
                                        <div className="right_block  wide_ ">
                                            <div className="middle  ">
                                                <div className="container">
                                                    <div className="row">
                                                        <CompanyForm
                                                            // currentCompany = {this.props.currentCompany}
                                                        >

                                                        </CompanyForm>
                                                    </div>

                                                    <div className="row">
                                                        <p>
                                                            Наши менеджеры всегда окажут необходимую помощь по
                                                            телефону: <a
                                                            href="tel:+375291456777">+375 (29) 1-456-777</a>.
                                                        </p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>

        )
    }
}

export default Company