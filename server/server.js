const express = require ('express');
const app = express();
const member = require ('./routes/member')

app.use('/member', member);

const PORT=55188;
app.listen(PORT, () => console.log(`Node.js Server is running on port ${PORT}`));