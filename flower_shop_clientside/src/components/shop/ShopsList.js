import React, {Component} from 'react'
import Shop from "./Shop";
import {withRouter} from "react-router-dom";


class ShopsList extends Component {

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
                                            <h1 id="pagetitle">Магазины вашей компании</h1>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>

                        <div className="col-8 m-0 p-0">
                            <div className="wrapper1 ">

                                <div className="container_inner clearfix ">
                                    <div className="right_block  wide_ ">
                                        <div className="middle  ">
                                            <div className="container">
                                                <div className="row">

                                                    <Shop>

                                                    </Shop>
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

export default withRouter(ShopsList)

