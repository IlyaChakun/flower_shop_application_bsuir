import React, {Component} from 'react'

import {withRouter} from 'react-router-dom'
import {Carousel, Form} from 'antd'
import MapContainer from "../common/map/MapContainer";

class Home extends Component {
    render() {
        const contentStyle = {
            height: '160px',
            color: '#fff',
            lineHeight: '160px',
            textAlign: 'center',
            background: '#364d79'
        }

        return (
            <div className="container-fluid">

                <div className="">
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

                <div className="">
                    тут хуярим цветы берем компонент списка из цветов

                </div>


                <div className="">
                    тут хуярим отзывы
                </div>


                <div className="">
                    тут хуярим карту и слева адерса магазов и при нажатие показывается магаз на карте

                    <div className="row">
                        <div className="col-3">

                        </div>
                        <div className="col-8">
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


