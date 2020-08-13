
import Customers from './Customers'
import Supplier from './Supplier'
import Orders from './Orders'
import Purchases from './Purchases'
import Product from './Product'
import React, { useState } from 'react';
import { Button } from '@material-ui/core';
import './App.css';

import Toolbar from '@material-ui/core/Toolbar';

import Container from '@material-ui/core/Container';



function App () {


  return ( 
    <div>
          <Toolbar>
   
        <Container maxWidth="md">
        <h1>Customers</h1> 
          <Customers />
          <h1>Product</h1>
          <Product/>
          {/* <h1>Suppliers</h1>
          <Supplier/> */}
          <h1>Orders</h1>
          <Orders/>
          <h1>Purchases</h1>
          <Purchases/>
        
        </Container>
        </Toolbar>
      </div>
  )
    

  
}

export default App;
