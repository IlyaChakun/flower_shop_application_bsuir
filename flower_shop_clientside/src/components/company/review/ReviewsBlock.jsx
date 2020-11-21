import React, {Component} from 'react'

import ReviewCard from "./ReviewCard";
import {getAllReviewsRequest} from "../../util/utilsAPI";
import AddReviewModal from "./AddReviewModal";

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
            <div className="content_wrapper_block front_review">
                <div className=" only-on-front">
                    <div className="top_block">
                        <h3>Отзывы</h3>
                        <a href="/about/reviews" className="pull-right font_upper muted">Все</a>
                        <span className="pull-right reviews">
                            <span
                                className="pull-right font_upper muted dark_link animate-load" data-event="jqm"
                                data-param-form_id="REVIEW" data-name="send_review">
                            <span>
                                <AddReviewModal />
                            </span>
                        </span>
                        </span>
                    </div>
                    <div className="item-views reviews compact more-item  ">
                        <div>
                            <div className="flex-viewport" style={{overflow: 'hidden', position: 'relative'}}>
                                <ul className="items slides flexbox row" style={{width: '600%', marginLeft: '0px'}}>

                                    {reviews}

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

export default ReviewsBlock

