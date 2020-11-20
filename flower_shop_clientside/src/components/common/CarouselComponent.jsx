import React from 'react'
import 'react-responsive-carousel/lib/styles/carousel.min.css'
import { Carousel } from 'react-responsive-carousel'

import image1 from '../../img/carosel/young-girl-531252_1920.jpg'
import image2 from '../../img/carosel/rose-165819_1920.jpg'
import image3 from '../../img/carosel/rose-1687884_1920.jpg'
import image4 from '../../img/carosel/rose-2101475_1920.jpg'
import image5 from '../../img/carosel/sparkler-677774_1920.jpg'

export default function CarouselComponent () {
  return (
    <div className="carousel-wrapper">
      <Carousel infiniteLoop useKeyboardArrows autoPlay showThumbs={false}>
        <div>
          <img src={image1}/>
        </div>
        <div>
          <img src={image2}/>
        </div>
        <div>
          <img src={image3}/>
        </div>
        <div>
          <img src={image4}/>
        </div>
        <div>
          <img src={image5}/>
        </div>
      </Carousel>
    </div>
  )
}
