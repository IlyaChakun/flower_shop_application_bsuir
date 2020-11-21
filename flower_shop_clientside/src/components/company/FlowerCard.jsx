import React, {Component} from 'react'
import {Avatar, Card} from 'antd'

const {Meta} = Card

class FlowerCard extends Component {
    render() {
        const {id, flowertype, cost} = this.props.flower
        return (

            <Card
                hoverable
                style={{width: 250}}
                cover={<img alt="example" style={{height:200}} src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"/>}
            >
                <Meta title="Europe Street beat" description="www.instagram.com"/>
                <h3>rosa avalanche</h3>
                <div className="preview-text">
                    {cost}
                </div>
            </Card>
        )
    }
}

export default FlowerCard
