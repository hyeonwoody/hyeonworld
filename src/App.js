import React from 'react';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Routes, Route, BrowserRouter} from 'react-router-dom'

import Init from './pages/Part/Init'

import Root from './pages/Main/Root'
import Game0 from './pages/Games/0/0';
import Game1 from './pages/Games/1';
import Game2 from './pages/Games/2';
import Game3 from './pages/Games/3';
import Game4 from './pages/Games/4';
import Game5 from './pages/Games/5';
import Game6 from './pages/Games/6';


import {QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

const queryClient = new QueryClient()

function App() {
  return (
    <QueryClientProvider client={queryClient}>

      <BrowserRouter>
        <Init / >
        <Routes>
          <Route path="/" element={<Root />} />
          <Route path="/game0" element={<Game0 />}/>
          <Route path="/game1" element={<Game1 />}/>
          <Route path="/game2" element={<Game2 />}/>
          <Route path="/game3" element={<Game3 />}/>
          <Route path="/game4" element={<Game4 />}/>
          <Route path="/game5" element={<Game5 />}/>
          <Route path="/game6" element={<Game6 />}/>
          
        </Routes>
      </BrowserRouter>
      {/* <ReactQueryDevtools initialIsOpen={false}/> */}
    </QueryClientProvider>
  );
}

export default App;