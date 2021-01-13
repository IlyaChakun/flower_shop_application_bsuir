import {createSlice} from "@reduxjs/toolkit"
import {getAllShops, getFlowersByShopIdRequest, getFlowersRequest} from "../../components/util/utilsAPI";

const initialState = {
    products: [],
    loading: true,
    errors: '',

    shops: [],
    shopId: '',
    shopValue: '',
    page: 1,
    size: 6,
    pagesCount: 0,
    totalPages: 0,
    totalElements: 0,

    searchString: ''
}
const productSlice = createSlice({
    name: "productsState",
    initialState,
    reducers: {
        setLoading: (state, payload) => {
            state.loading = payload
        },
        setErrors: (state, payload) => {
            state.errors = payload
        },
        setProducts: (state, payload) => {
            state.products = payload
        },
        setTotalPages: (state, payload) => {
            state.totalPages = payload
        },
        setTotalElements: (state, payload) => {
            state.totalElements = payload
        },
        setPage: (state, payload) => {
            state.page = payload
        },
        setSize: (state, payload) => {
            state.size = payload
        },
        setShops: (state, payload) => {
            state.shops = payload
        },
        setShopValue: (state, payload) => {
            state.shopValue = payload
        },
        setShopId: (state, payload) => {
            state.shopId = payload
        },
    },
})
export const {
    setLoading,
    setErrors,
    setProducts,
    setTotalPages,
    setTotalElements,
    setPage,
    setSize,
    setShops,
    setShopValue,
    setShopId,
} = productSlice.actions
export default productSlice.reducer
export const productSelector = (state) => {
    return state.productsState;
}


export const getProducts = (searchCriteria, shopId = null) => {
    return async dispatch => {
        // dispatch(setLoading(true))
        try {
            let promise
            if (shopId === null) {
                promise = getFlowersRequest(searchCriteria)
            } else {
                promise = getFlowersByShopIdRequest(searchCriteria, shopId)
            }

            if (!promise) {
                return;
            }
            promise
                .then(response => {
                    dispatch(setProducts(response.objects.slice()))
                    dispatch(setTotalPages(response.totalPages))
                    dispatch(setTotalElements(response.totalElements))
                    dispatch(setLoading(false))
                })
        } catch (error) {
            dispatch(setErrors(error))
            dispatch(setLoading(false))
        }
    }
}


export const getShops = () => {
    return async dispatch => {
        // dispatch(setLoading(true))
        try {
            const promise = getAllShops()

            if (!promise) {
                return;
            }
            promise
                .then(response => {
                    dispatch(setShops(response.objects.slice()))
                    dispatch(setShopValue(response.objects[0] === null ? null : response.objects[0].contacts.address))
                    dispatch(setShopId(response.objects[0] === null ? null : response.objects[0].id))
                    dispatch(setLoading(false))
                })
        } catch (error) {
            dispatch(setErrors(error))
            dispatch(setLoading(false))
        }
    }
}
