import axios from 'axios';
import React, { useEffect, useState } from 'react';


function Ain() {
  const [show, setShow] = useState([])  


  axios.post('/member/truthShow', null, {

  })
            .then (res => {
              console.log("Show 결과 " , res.data);
              setShow(res.data)
              })
                

useEffect(() => {
  
},
[show])
  return (
 <main >
      <div>{show.name}</div>
      <div>{show.Qfirst}</div>
      <div>{show.Qsecond}</div>
      <div>{show.Qthird}</div>
    </main>
  );
}

export default Ain;