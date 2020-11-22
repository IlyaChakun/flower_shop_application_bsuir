import React, {Component} from 'react'

import MapContainer from "../../common/map/MapContainer";
import "./ShopsBlock.css"
import {Link} from "react-router-dom";
import ShopCard from "../../shop/ShopCard";

class ShopsBlock extends Component {
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

        const shopElements = this.state.shops
            .map(shop => (<ShopCard key={shop.id} shop={shop}/>))

        return (
            <div className="row shops-front">
                <div className="shops-top-info d-flex flex-row justify-content-between">
                    <h3>Адреса магазинов</h3>
                    <Link to="/company/shops">ПЕРЕЙТИ В РАЗДЕЛ</Link>
                </div>

                <div className="col-5">
                    {shopElements}
                </div>
                <div className="col-7">
                    <MapContainer
                        google={this.props.google}
                        center={{lat: 53.893009, lng: 27.567444}}
                        height='300px'
                        zoom={14}
                    />
                </div>
            </div>
        )
    }
}

export default ShopsBlock

