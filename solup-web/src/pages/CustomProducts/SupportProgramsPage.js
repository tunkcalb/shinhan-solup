import React, { Component } from 'react';
import SpecialtyProgramsList from '../../components/SupportProgramsPage/SpecialtyProgramsList'
import GrowthProgramsList from '../../components/SupportProgramsPage/GrowthProgramsList'
import Header from '../../components/Header';
import NavBar from '../../components/Footer';

class SupportProgramsPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activeTab: 'specialtyProgram', // 초기 활성 탭은 '소상공인특화'입니다.
    };
  }

  handleTabChange = (tab) => {
    this.setState({ activeTab: tab });
  };

  render() {
    const { activeTab } = this.state;

    return (
      <div>
        <Header title="지원 프로그램" />
        <div className="tabContainer">
          <span className="span-container">
            <span
              className={activeTab === 'specialtyProgram' ? 'active' : ''}
              onClick={() => this.handleTabChange('specialtyProgram')}
            >
              소상공인특화
            </span>
          </span>
          <span className='span-container'>
            <span
              className={activeTab === 'growthProgram' ? 'active' : ''}
              onClick={() => this.handleTabChange('growthProgram')}
            >
              성장지원
            </span>
          </span>
        </div>


        <div className="tab-content">
          {activeTab === 'specialtyProgram' && <SpecialtyProgramsList />}
          {activeTab === 'growthProgram' && <GrowthProgramsList />}
        </div>

        <NavBar />
      </div>
    );
  }
}

export default SupportProgramsPage;
