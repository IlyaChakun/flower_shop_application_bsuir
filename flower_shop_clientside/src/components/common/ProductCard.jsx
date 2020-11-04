import React from 'react'
import image from '../../images/shop/products/product-7.jpg'


function ProductCard() {
    return (
        <div className="col-md-4">
            <div className="product-item">
                <div className="product-thumb">
                    <span className="bage">Sale</span>
                    <img className="img-responsive" src={image}
                         alt="product-img"/>
                    <div className="preview-meta">
                        <ul>
                            <li>
									<span data-toggle="modal" data-target="#product-modal">
										<i className="tf-ion-ios-search-strong"></i>
									</span>
                            </li>
                            <li>
                                <a href="#"><i className="tf-ion-ios-heart"></i></a>
                            </li>
                            <li>
                                <a href=""><i className="tf-ion-android-cart"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div className="product-content">
                    <h4><a href="product-single.html">Rainbow Shoes</a></h4>
                    <p className="price">$200</p>
                </div>
            </div>
        </div>
    );
}

export default ProductCard;



