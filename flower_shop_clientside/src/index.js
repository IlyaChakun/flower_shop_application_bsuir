import React from 'react';
import ReactDOM from 'react-dom';

import App from './app/App';
import 'bootstrap/dist/css/bootstrap.css';
import './index.css';

// <!-- Themefisher Icon font -->
import './plugins/themefisher-font/style.css'
// <!-- Revolution Slider -->
import './plugins/revolution-slider/revolution/fonts/pe-icon-7-stroke/css/pe-icon-7-stroke.css'
import './plugins/revolution-slider/revolution/fonts/font-awesome/css/font-awesome.css'
// <!-- REVOLUTION STYLE SHEETS -->
import './plugins/revolution-slider/revolution/css/settings.css'
import './plugins/revolution-slider/revolution/css/layers.css'
import './plugins/revolution-slider/revolution/css/navigation.css'

// <!-- Main Stylesheet -->
import './css/style.css'

import reportWebVitals from './app/reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
