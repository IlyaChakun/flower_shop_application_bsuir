import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import App from './app/App'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap-grid.css'
import {Provider} from 'react-redux'

import store from "./redux/store";
import {BrowserRouter} from "react-router-dom";
// import 'antd/dist/antd.css'


ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </Provider>,
    document.getElementById('root')
)
