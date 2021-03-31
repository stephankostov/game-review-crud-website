import React, { Component } from "react";
import DeveloperAPIService from "../service/DeveloperAPIService";

class DeveloperAddComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: 99999,
            name: '',
            address: '',
            message: null
        }
        this.refreshDeveloperList = this.refreshDeveloperList.bind(this);
    }

    onChange = (e) => {
        this.setState({[e.target.name]: e.target.value});
        console.log(this.state)
    }

    onSubmit = (e) => {
        e.preventDefault();
        let developer = {developerId: 99999, name: this.state.name, address: this.state.address}
        DeveloperAPIService.createDeveloper(developer)
            .then(response => {
                this.setState({message: 'Developer Added Successfully.'})
                this.props.history.push('/games-add')
            })
    }

    render() {
        return (
            <div>
                <h2>Add Developer</h2>
                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                <form>

                    <div className="form-group">
                        <label>Name:</label>
                        <input type="text" placeholder="name" name="name"
                               className="form-control" value={this.state.name} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>Address:</label>
                        <input type="text" placeholder="address" name="address"
                               className="form-control" value={this.state.address} onChange={this.onChange}/>
                    </div>

                    <button className="btn btn-success" onClick={this.onSubmit}>Save</button>

                </form>
            </div>
        )
    }
}

export default DeveloperAddComponent;