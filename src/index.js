import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import './index.css';
import App from './App';

import Game0 from './pages/Games/0';
import Game1 from './pages/Games/1';
import Game2 from './pages/Games/2';
import Game3 from './pages/Games/3';
import Game4 from './pages/Games/4';
import Game5 from './pages/Games/5';
import Game6 from './pages/Games/6';
import Monitor from './pages/Main/Monitor/Monitor'

import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <main>
  <BrowserRouter>
  <Routes>
    <Route path="/" element={<App />} />
    <Route path="/game0" element={<Game0 />}/> 
    <Route path="/game1" element={<Game1 />}/> 
    <Route path="/game2" element={<Game2 />}/> 
    <Route path="/game3" element={<Game3 />}/> 
    <Route path="/game4" element={<Game4 />}/> 
    <Route path="/game5" element={<Game5 />}/> 
    <Route path="/game6" element={<Game6 />}/> 
    <Route path="/monitor" element={<Monitor />}/> 
    
  </Routes>
  {/* <App /> */}
  
  </BrowserRouter>
    
    </main>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
