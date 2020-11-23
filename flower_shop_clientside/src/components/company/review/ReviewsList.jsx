import React, {Component} from 'react'

import ReviewCard from "./ReviewCard";
import {getAllReviewsRequest} from "../../util/utilsAPI";
import AddReviewModal from "./AddReviewModal";
import './ReviewsBlock.css'
import {Link} from "react-router-dom";
import {List} from "antd";

class ReviewsList extends Component {
    state = {
        reviews: [],

        page: 1,
        size: 3,
        pagesCount: 0,

        searchString: '',

        totalPages: 0,
        totalElements: 0,

        isLoading: false
    }


    componentDidMount() {
        //load list
        this.loadList(this.state.page, this.state.size)
    }


    loadList = (page, size) => {

        const searchCriteria = {
            page: page,
            size: size
        };

        const promise = getAllReviewsRequest(searchCriteria);
        if (!promise) {
            return;
        }
        this.extractPromise(promise);
    };


    extractPromise = (promise) => {

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {

                this.setState({
                    reviews: response.objects.slice(),
                    totalPages: response.totalPages,
                    totalElements: response.totalElements,
                });

            }).catch(() => {
            this.setState({
                isLoading: false
            });
        });
    };


    render() {
        const reviews = this.state.reviews
            .map(review => (<ReviewCard key={review.id} review={review}/>))

        return (
            <div className="container-fluid">
                <div className="review-block col-10 mx-auto">
                    <div className="top_block d-flex flex-row justify-content-between">
                        <div className="col-4">
                            <h3>Отзывы</h3>
                        </div>
                        <div>
                            <AddReviewModal/>
                        </div>
                    </div>
                    <div className="reviews">
                        <div className="flex-viewport" style={{overflow: 'hidden', position: 'relative'}}>

                            <List
                                grid={{
                                    gutter: 70,
                                    column: 3,
                                }}

                                pagination={{

                                    loading: this.state.isLoading,
                                    showSizeChanger: true,

                                    defaultCurrent: Number(this.state.page),
                                    defaultPageSize: Number(this.state.size),

                                    pageSizeOptions: ["3", "6", "9"],
                                    position: "bottom",

                                    total: this.state.totalElements,

                                    onShowSizeChange: this.onSizeChangeHandler,
                                    onChange: this.onPageChangeHandler,

                                    loadMore: this.loadMore
                                }}

                                dataSource={reviews}

                                renderItem={item => (
                                    <List.Item>
                                        {item}
                                    </List.Item>
                                )}
                            />

                        </div>
                    </div>
                </div>
            </div>


        )
    }


    onSizeChangeHandler = (page, size) => {

        this.setState({
            page: page,
            size: size
        });
        this.loadList(page, size);
    };

    onPageChangeHandler = (pageNumber) => {

        console.log('onPageChangeHandler')
        console.log('pageNumber', pageNumber)
        console.log('totalElements', this.state.totalElements)
        console.log('totalPages', this.state.totalPages)

        this.setState({
            page: pageNumber
        });


        this.loadList(pageNumber, this.state.size);
    };

    loadMore = () => {

        console.log('LOAD MORE WORKS')

        this.loadList(this.state.page + 1, this.state.size);
    }

}

export default ReviewsList

