import React from 'react';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Routes, Route, BrowserRouter} from 'react-router-dom'

import Root from './pages/Main/Root'

import Monitor from './pages/Main/Monitor/Monitor.js'

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Root />} />

          <Route path="/monitor" element={<Monitor />}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;