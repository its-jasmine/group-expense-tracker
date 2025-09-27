import './index.css'
import Header from './components/Header'
import Main from './components/Main'
import api from './api'
import { useState, useEffect } from 'react'

function App() {
  const [summaryData, setSummaryData] = useState({})
  useEffect(() => {
    api.getSummary().then(response => {
      console.log(response)
      setSummaryData(response)
    })
  }, [])

  return (
    <>
      <Header 
        memberCount={summaryData.hasOwnProperty('memberCount') ? summaryData.memberCount : 0}
        expenseCount={summaryData.hasOwnProperty('expenseCount') ? summaryData.expenseCount : 0}
        expenseSum={summaryData.hasOwnProperty('expenseSum') ? summaryData.expenseSum : 0}
      />
      <Main />
    </>
  )
}

export default App
