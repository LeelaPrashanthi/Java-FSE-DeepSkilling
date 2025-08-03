// Enzyme setup for React 16
import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

// Optional: keep jest-dom for other matchers if needed
import '@testing-library/jest-dom';

configure({ adapter: new Adapter() });
