import React from 'react'
import ProductCard from "./ProductCard";


function Content() {
    return (
        <>


            <section className="product-category section">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="title text-center">
                                <h2>Product Category</h2>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="category-box">
                                <a href="">
                                    <img src="../../images/shop/category/category-1.jpg" alt=""/>
                                    <div className="content">
                                        <h3>Clothes Sales</h3>
                                        <p>Shop New Season Clothing</p>
                                    </div>
                                </a>
                            </div>
                            <div className="category-box">
                                <a href="">
                                    <img src="../../images/shop/category/category-2.jpg" alt=""/>
                                    <div className="content">
                                        <h3>Smart Casuals</h3>
                                        <p>Get Wide Range Selection</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div className="col-md-6">
                            <div className="category-box category-box-2">
                                <a href="">
                                    <img src="../../images/shop/category/category-3.jpg" alt=""/>
                                    <div className="content">
                                        <h3>Jewellery</h3>
                                        <p>Special Design Comes First</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="products section bg-gray">
                <div className="container">
                    <div className="row">
                        <div className="title text-center">
                            <h2>Лучшие предложения</h2>
                        </div>
                    </div>
                    <div className="row">

                        <ProductCard/>
                        <ProductCard/>
                        <ProductCard/>
                        <ProductCard/>

                        {/*// dal*/}
                        <div className="modal product-modal fade" id="product-modal">
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <i className="tf-ion-close"></i>
                            </button>
                            <div className="modal-dialog " role="document">
                                <div className="modal-content">
                                    <div className="modal-body">
                                        <div className="row">
                                            <div className="col-md-8 col-sm-6 col-xs-12">
                                                <div className="modal-image">
                                                    <img className="img-responsive"
                                                         src="../../images/shop/products/modal-product.jpg"
                                                         alt="product-img"/>
                                                </div>
                                            </div>
                                            <div className="col-md-4 col-sm-6 col-xs-12">
                                                <div className="product-short-details">
                                                    <h2 className="product-title">GM Pendant, Basalt Grey</h2>
                                                    <p className="product-price">$200</p>
                                                    <p className="product-short-description">
                                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
                                                        iusto
                                                        nihil cum. Illo laborum numquam rem aut officia dicta cumque.
                                                    </p>
                                                    <a href="cart.html" className="btn btn-main">Add To Cart</a>
                                                    <a href="product-single.html" className="btn btn-transparent">View
                                                        Product
                                                        Details</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/*// modal*/}

                    </div>
                </div>
            </section>

            {/*//           Start Call To Action*/}
            {/*====================================*/}
            <section className="call-to-action bg-gray section">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 text-center">
                            <div className="title">
                                <h2>SUBSCRIBE TO NEWSLETTER</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugiat,
                                    facilis numquam impedit ut sequi.
                                    Minus facilis vitae excepturi sit laboriosam.
                                </p>
                            </div>
                            <div className="col-lg-6 col-md-offset-3">
                                <div className="input-group subscription-form">
                                    <input type="text" className="form-control" placeholder="Enter Your Email Address"/>
                                    <span className="input-group-btn">
				        <button className="btn btn-main" type="button">Subscribe Now!</button>
				      </span>
                                </div>
                                {/*// nput-group*/}
                            </div>
                            {/*// col-lg-6*/}

                        </div>
                    </div>
                    {/*// d row*/}
                </div>
                {/*// d container*/}
            </section>
            {/*// d section*/}

        </>
    );
}

export default Content;



