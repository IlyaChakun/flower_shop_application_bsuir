import React, {Component} from 'react'

import {withRouter} from 'react-router-dom'
import {Carousel, Form} from 'antd'
import MapContainer from '../common/map/MapContainer'
import ShopCard from "../shop/ShopCard";
import ReviewsBlock from "../company/ReviewsBlock";
import FlowersList from "../company/FlowersList";

class Home extends Component {
    state = {
        shops: [
            {
                "id": 11,
                "dateOfCreation": "2020-11-19 00.20",
                "dateOfLastUpdate": "2020-11-19 00.20",
                "contacts": {
                    "id": 12,
                    "firstPhoneNumber": "+375289951449",
                    "secondPhoneNumber": "+37574529656",
                    "email": "shop2@hu.lu",
                    "city": "Brest",
                    "address": "Pobedi str.122"
                },
                "workingHours": {
                    "hours": " s 10 do 18"
                },
                "shopProducts": []
            },
            {
                "id": 13,
                "dateOfCreation": "2020-11-19 00.21",
                "dateOfLastUpdate": "2020-11-19 00.21",
                "contacts": {
                    "id": 14,
                    "firstPhoneNumber": "+375239971449",
                    "secondPhoneNumber": "+37574545656",
                    "email": "shop3@hu.lu",
                    "city": "Brest",
                    "address": "Napoleona Ordi str.18"
                },
                "workingHours": {
                    "hours": " s 10 do 18"
                },
                "shopProducts": []
            }
        ],

        isLoading: false
    }

    render() {
        const contentStyle = {
            height: '160px',
            color: '#fff',
            lineHeight: '160px',
            textAlign: 'center',
            background: '#364d79'
        }

        const shopElements = this.state.shops
            .map(shop => (<ShopCard key={shop.id} shop={shop}/>))


        return (
            <div className="container">

                <div className="col-12">
                    <div className="row">
                        <Carousel autoplay>
                            <div>
                                <h3 style={contentStyle}>1</h3>
                            </div>
                            <div>
                                <h3 style={contentStyle}>2</h3>
                            </div>
                            <div>
                                <h3 style={contentStyle}>3</h3>
                            </div>
                            <div>
                                <h3 style={contentStyle}>4</h3>
                            </div>
                        </Carousel>
                    </div>

                    <div className="row">
                        <FlowersList/>
                    </div>

                    <div className="row">
                        <ReviewsBlock/>
                    </div>

                    <div className="row">
                        <div className="col-sm-5">
                            {shopElements}
                        </div>
                        <div className="col-sm-7">
                            <MapContainer
                                google={this.props.google}
                                center={{lat: 53.893009, lng: 27.567444}}
                                height='300px'
                                zoom={14}
                            />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(Home)
