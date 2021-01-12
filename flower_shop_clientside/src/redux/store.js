import {configureStore, getDefaultMiddleware} from '@reduxjs/toolkit'
import ProductsSliceReducer from "./reducers/ProductsSliceReducer";

const middleware = [
    ...getDefaultMiddleware(),
    /*YOUR CUSTOM MIDDLEWARES HERE*/
];

const store = configureStore({
    reducer: {
        productsState: ProductsSliceReducer,
    },
    middleware
})

export default store