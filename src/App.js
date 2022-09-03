import React, { useEffect, useState } from 'react';
import { BrowserRouter,Routes, Route } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Login from './pages/Login/Login';
import Main from './pages/Main/Main';

function App() {
  const [isLogin, setIsLogin] = useState(false)


  // const db = require ('./config/db.json')
  // for (let i=0 ; i<db.games.length;i++){
  //   console.log(db.games[i].name)
  // }
  useEffect(()=>{
    if (sessionStorage.getItem('memberName') === null){
      console.log('isLogVVin ?? :: ',isLogin)
    }
    else{
      setIsLogin(true)
      console.log('isLogin ?? ::', isLogin)
    }

  })
  return (
    <div className='App'>
        {isLogin ? 
          <Main isLogin={isLogin}/>:
          <Login />}
          
    </div>
  );
}



export default App;
