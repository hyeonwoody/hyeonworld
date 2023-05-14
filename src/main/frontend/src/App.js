import React, { useState, useEffect } from 'react';
import './App.css';
import customAxios from './customAxios.ts';

function App() {
  // IP주소 변수 선언
  const [ip, setIp] = useState('');

  // IP주소 값을 설정합니다.
  function callback(data) {
    setIp(data);
  }

  // 첫번째 렌더링을 다 마친 후 실행합니다.
  useEffect(
      () => {
        // 클라이언트의 IP주소를 알아내는 백엔드의 함수를 호출합니다.
          console.log("ㅁㅇㅁㅇㄴㅇㄴ");
        customAxios('/ip', callback);
      }, []
  );

  return (
      <div className="App">
        <header className="App-header">
          이 기기의 IP주소는 {ip}입니다.
        </header>
      </div>
  );
}

export default App;

// import React, {useState, useEffect} from 'react';
//
// function App() {
//     const [message, setMessage]=useState([]);
//     useEffect(()=>{
//         fetch("/hello")
//             .then((res)=>{
//                 console.log(res.json());
//                 return res.json();
//             })
//             .then((data)=>{
//
//                 setMessage(data);
//             });
//     },[]);
//     return (
//         <div className="App">
//             <header className="App-header">
//                 // 기본코드
//                 <ul>
//                     {message.map((v,idx)=><li key={`${idx}-${v}`}>{v}</li>)}
//                 </ul>
//             </header>
//         </div>
//     );
// }
//
// export default App;