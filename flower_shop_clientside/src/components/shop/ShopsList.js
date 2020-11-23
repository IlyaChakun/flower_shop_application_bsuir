import React, {Component} from 'react'
import ShopCard from './ShopCard'
import {withRouter} from 'react-router-dom'
import MapContainer from "../common/map/MapContainer";
import AddShopModal from "./AddShopModal";

class ShopsList extends Component {

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
                }
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
                }
            }
        ],

        isLoading: false
    }

    render() {

        const shopElements = this.state.shops
            .map(shop => (<ShopCard key={shop.id} shop={shop}/>))

        return (
            <div className="container-fluid">
                <div className="contacts_map mb-5">
                    <div id="map-block" style={{height: '550px', width: '100%'}}>
                        <MapContainer
                            google={this.props.google}
                            center={{lat: 53.893009, lng: 27.567444}}
                            height='550px'
                            zoom={14}
                        />
                    </div>
                </div>

                <h1 className="text-center mb-3">Наши магазины</h1>

                <div className="stores-list">
                    <div className="items">
                        {shopElements}
                    </div>
                </div>
                <div>
                    <AddShopModal/>
                </div>

            </div>
        )
    }
}

export default withRouter(ShopsList)
