import React, {Component} from "react";

class UserInfo extends Component {

    state = {
        user: null
    }


    componentDidMount() {
        const API = "http://localhost:8080/api/user/1";
        console.log("ComponentDidMount")

        fetch(API, {mode: "cors"})
            .then(response => {
                if (response.ok) {
                    return response;
                }
                throw response.status;
            })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                this.setState({
                        user: data
                    }
                )
            })
            .catch(error => console.log(error));
    }

    render() {
        const user = this.state.user;
        console.log("user: " + user);
        return (
            <div>
                <h1>Welcome {user ? user.firstName : ""}</h1>
                <h2>Account status: {user ? user.active.toString() : ""}</h2>
            </div>
        )
    }

}

export default UserInfo;
