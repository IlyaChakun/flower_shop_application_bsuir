import React, { Component } from 'react'

import { withRouter } from 'react-router-dom'
import ReviewsList from '../company/review/ReviewsList'
import CarouselComponent from '../common/carousel/CarouselComponent'
import ShopsBlock from '../shop/ShopsBlock'
import FlowersList from '../products/flower/FlowersList'
import BouquetList from '../products/bouquet/BouquetList'

class Home extends Component {
  render () {
    return (
      <div className="container-fluid">
        <div className="col-12">
          <div className="row mb-5">
            <CarouselComponent/>
          </div>

          <div className="row mb-2">
            <FlowersList/>
          </div>

          <div className="row mb-2">
            <BouquetList/>
          </div>

          <div className="row">
            <ReviewsList/>
          </div>

          <ShopsBlock/>

        </div>
      </div>
    )
  }
}

export default withRouter(Home)
