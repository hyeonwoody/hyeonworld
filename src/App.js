import React, { useEffect, useState } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Login from './components/pages/Login/Login';
import Main from './components/pages/Main/Main';

function App() {
  const [isLogin, setIsLogin] = useState(false)

  useEffect(()=>{
    if (sessionStorage.getItem('memberName') === null){
      console.log('isLogin ?? :: ',isLogin)
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
