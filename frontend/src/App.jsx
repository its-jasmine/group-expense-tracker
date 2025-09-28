import './index.css'
import Header from './components/Header'
import Main from './components/Main'
import api from './api'
import { useState, useEffect } from 'react'

function App() {
  const [summaryData, setSummaryData] = useState({})
  useEffect(() => {
    api.getSummary().then(response => {
      console.log('api response', response)
      setSummaryData(response)
    })
  }, [])

  return (
    <>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
      <Header 
        memberCount={summaryData.hasOwnProperty('memberCount') ? summaryData.memberCount : 0}
        expenseCount={summaryData.hasOwnProperty('expenseCount') ? summaryData.expenseCount : 0}
        expenseSum={summaryData.hasOwnProperty('expenseSum') ? summaryData.expenseSum : 0}
      />
      <Main summaryData={summaryData}/>
    </>
  )
}

export default App
