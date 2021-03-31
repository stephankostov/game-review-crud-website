import React, { Component } from 'react';
import DeveloperAPIService from "../service/DeveloperAPIService";

class DeveloperComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            name: '',
            address: '',
            gamesList: []
        }
        this.loadDeveloper = this.loadDeveloper.bind(this);
    }

    componentDidMount() {
        this.loadDeveloper();
    }

    loadDeveloper() {
        DeveloperAPIService.retrieveDeveloper(this.state.id)
            .then(response => {
                let developer = response.data
                console.log(developer)
                this.setState({
                    name: developer.name,
                    address: developer.address
                })
            });
    }

    render() {

        let { name, address } = this.state;

        return (
            <div>
                <h3>Game Details</h3>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{name}</td>
                        <td>{address}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        )
    }
}

export default DeveloperComponent;