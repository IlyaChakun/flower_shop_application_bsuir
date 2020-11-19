import React, { Component } from 'react'

class ReviewCard extends Component {
  render () {
    const {id, dateOfCreation, name, phoneNumber, email, text, rating} = this.props.review
    return (
      <li className="item-wrapper col-xs-12"
        style={{ width: '407.333px', marginRight: '0px', float: 'left', display: 'block' }}>
        <div className="item clearfix  no_img  bordered box-shadow">
          <div className="top_wrapper clearfix">
            <div className="top-info">
              <div className="wrap muted">
                <span className="date font_upper">{dateOfCreation}</span>
              </div>
              <div className="title font_md pull-left">
                {name}
              </div>
              <div className="votes_block big pull-right">
                <div className="ratings">
                  <div className="inner_rating">
                    <div className="item-rating filed"></div>
                    <div className="item-rating filed"></div>
                    <div className="item-rating filed"></div>
                    <div className="item-rating filed"></div>
                    <div className="item-rating filed"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="body-info">

            <div className="votes_block">
              <div className="ratings">
                <div className="inner_rating">
                  <div className="item-rating filed"></div>
                  <div className="item-rating filed"></div>
                  <div className="item-rating filed"></div>
                  <div className="item-rating filed"></div>
                  <div className="item-rating filed"></div>
                </div>
              </div>
            </div>
            <div className="clearfix"></div>
            <div className="preview-text">
              {text}
            </div>
          </div>
        </div>
      </li>
    )
  }
}

export default ReviewCard
