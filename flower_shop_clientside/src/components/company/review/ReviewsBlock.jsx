import React, {Component} from 'react'

import ReviewCard from "./ReviewCard";
import {getAllReviewsRequest} from "../../util/utilsAPI";
import AddReviewModal from "./AddReviewModal";
import './ReviewsBlock.css'
import {Link} from "react-router-dom";

class ReviewsBlock extends Component {
    state = {
        reviews: [],

        isLoading: false
    }

    componentDidMount() {
        this.fetchReviews()
    }


    fetchReviews = () => {
        const reviews = getAllReviewsRequest()
        console.log('reviews: ' + reviews)
        reviews
            .then(response => {
                this.setState({
                    reviews: response.objects
                })
            })
    }

    render() {
        const reviews = this.state.reviews
            .map(review => (<ReviewCard key={review.id} review={review}/>))

        return (
            <div className="review-front">
                <div className="review-block col-10 mx-auto">
                    <div className="top_block d-flex flex-row justify-content-between">
                        <div className="col-4">
                            <h3>Отзывы</h3>
                        </div>
                        <div className="col-4 d-flex flex-row justify-content-between">
                            <AddReviewModal/>
                            <Link to="/reviews">Все</Link>
                        </div>
                    </div>
                    <div className="reviews">
                        <div className="flex-viewport" style={{overflow: 'hidden', position: 'relative'}}>
                            <ul className="items slides flexbox row" style={{width: '600%', marginLeft: '0px'}}>
                                {reviews}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


        )
    }
}

export default ReviewsBlock

