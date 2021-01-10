import {configureStore} from "@reduxjs/toolkit";
import productSliceReducer from "./reducers/productSliceReducer";


const store = configureStore({
    reducer: {
        products: productSliceReducer,
        // anyOtherStore: anyOtherSlice
    },
})

export default store
