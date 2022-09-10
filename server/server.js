const express = require ('express');
const app = express();
const member = require ('./routes/member');
const game = require('./routes/game');
const stage = require('./routes/stage');

app.use('/game', game);
app.listen(3002, ()=> console.log(`Node.js Server is running on port 3002`));


app.use('/member', member);
app.listen(3001, ()=> console.log(`Node.js Server is running on port 3001`));

app.use('/stage', stage);
app.listen(3003, ()=> console.log(`Node.js Server is running on port 3003`));
