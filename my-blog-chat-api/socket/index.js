const socketIo = require('socket.io')
const socketServer=(server)=>{
    const io=socketIo(server)
    io.on('connection',(socket)=>{
        socket.on('join',async (user)=>{
            console.log("new user joined")
        } )
    })
}