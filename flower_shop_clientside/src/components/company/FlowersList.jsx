import React, {Component} from 'react'

import ReviewCard from "./review/ReviewCard";
import FlowerCard from "./FlowerCard";

class FlowersList extends Component {
    state = {
        flowers: [
            {
                id: 41,
                flowerType: "роза аваланш",
                cost: 25
            },
            {
                id: 31,
                flowerType: "роза аваланш пинч",
                cost: 30
            },
            {
                id: 21,
                flowerType: "роза ноктюрн",
                cost: 14
            }
        ],

        isLoading: false
    }

    render() {
        const flowers = this.state.flowers
            .map(flower => (<FlowerCard key={flower.id} flower={flower}/>))

        return (
            <div className="content_wrapper_block front_review">
                <div className="maxwidth-theme only-on-front">
                    <div className="top_block">
                        <h3>Наши цветы</h3>
                        <a href="/about/reviews" className="pull-right font_upper muted">Все</a>
                        <span className="pull-right reviews">
                            <span
                                className="pull-right font_upper muted dark_link animate-load" data-event="jqm"
                                data-param-form_id="REVIEW" data-name="send_review">
                            <span>Оставить отзыв</span>
                        </span>
                        </span>
                    </div>
                    <div className="item-views reviews compact more-item  ">
                        <div>
                            <div className="flex-viewport" style={{overflow: 'hidden', position: 'relative'}}>
                                <ul className="items slides flexbox row" style={{width: '600%', marginLeft: '0px'}}>

                                    {flowers}

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

export default FlowersList

