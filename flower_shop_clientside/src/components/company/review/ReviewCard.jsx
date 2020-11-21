import React, {Component} from 'react'
import {Card} from "antd"

const {Meta} = Card

class ReviewCard extends Component {
    render() {
        const {id, dateOfCreation, name, phoneNumber, email, text, rating} = this.props.review
        return (

            <Card
                hoverable
                style={{width: 250}}
            >
                <Meta title="Europe Street beat" description="www.instagram.com"/>
                <div className="title">
                    {name}
                    {phoneNumber}
                    {email}
                    {dateOfCreation}
                </div>
                <div className="rating">
                    {rating}
                </div>
                <div className="preview-text">
                    {text}
                </div>
            </Card>
        )
    }
}

export default ReviewCard
