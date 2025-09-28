
// Create a NumberFormat object for CAD Dollars in English
const formatterCAD = new Intl.NumberFormat('en-CA', { // TODO move to separate formatting helper file
style: 'currency',
currency: 'CAD',
currencyDisplay: 'narrowSymbol'
});

export default formatterCAD;