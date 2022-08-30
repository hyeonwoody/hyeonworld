const express = require ('express');
const app = express();
const member = require ('./routes/member');

app.use('/member', member);

const PORT = 3001;
app.listen(PORT, ()=> console.log(`Node.js Server is running on port ${PORT}`));
