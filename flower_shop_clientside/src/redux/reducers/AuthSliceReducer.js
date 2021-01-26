import {createSlice} from "@reduxjs/toolkit"
import {getCurrentUserRequest, getProductsByShopIdRequest, getProductsRequest} from "../../components/util/utilsAPI";

const initialState = {
    loading: true,
    errors: '',
    currentUser: null,
    isAuthenticated: false
}
const authSlice = createSlice({
    name: "authState",
    initialState,
    reducers: {
        setLoading: (state, payload) => {
            state.loading = payload
        },
        setErrors: (state, payload) => {
            state.errors = payload
        },
        setCurrentUser: (state, payload) => {
            state.currentUser = payload
        },
        setIsAuthenticated: (state, payload) => {
            state.isAuthenticated = payload
        }
    },
})
export const {
    setLoading,
    setErrors,
    setCurrentUser,
    setIsAuthenticated
} = authSlice.actions

export default authSlice.reducer

export const authSelector = (state) => {
    return state.authState;
}


export const getCurrentUser = () => {
    return async dispatch => {
        dispatch(setLoading(true))
        try {
            let promise = getCurrentUserRequest()

            if (!promise) {
                return;
            }
            promise
                .then(response => {
                    dispatch(setCurrentUser(response))
                    dispatch(setIsAuthenticated(true))
                    dispatch(setLoading(false))
                })
        } catch (error) {
            dispatch(setErrors(error))
            dispatch(setLoading(false))
        }
    }
}
