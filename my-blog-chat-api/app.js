const express = require('express')
const {Server} = require("socket.io");
const http = require('http')
const router = require('./routes/users')
const cors = require('cors')
const PORT = 5000
const app = express()
const server = http.createServer(app)
const io = new Server(server);
app.use(cors())
app.use(router)
// socket nhận 1 connection
io.on('connection', (socket) => {
    console.log('socket connected')
    socket.on("room", (room) => {
        if (!checkExistRoom(room)) {
            console.log(room)
            socket.emit("add_room", "add room ne")
            socket.join(room.name)
        }
    })
    socket.on("leave_room", (room) => {
        socket.leave(room.name)
    })
})

const checkExistRoom = (room) => {
    if (io.sockets.adapter.rooms.get(room.name)) {
        return true;
    }
    return false;
}

server.listen(PORT, () => console.log(`Server has started on port ${PORT}`))
module.exports.io = io