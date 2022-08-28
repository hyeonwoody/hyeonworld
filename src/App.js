import React from 'react';
import './App.css';
import Login from './components/Login';


function App() {
  return (
    <div className="App">
                {/* <div className="form-floating">
                    
                    <input type="email" className="form-control" id="floatingInput"></input>
                    <label for="floatingInput">성함</label>
                </div>
                <div className="form-floating">
                    <button type="button" class="btn btn-primary">로그인</button>
                </div> */}
      <Login />
    </div>
  )
}

export default App;