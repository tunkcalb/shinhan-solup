import React, { Component } from 'react';
import SpecialtyProgramsList from '../../components/SupportProgramsPage/SpecialtyProgramsList'
import GrowthProgramsList from '../../components/SupportProgramsPage/GrowthProgramsList'


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
        <h1>지원 프로그램 목록</h1>
        <div className="tab-buttons">
          <button
            className={activeTab === 'specialtyProgram' ? 'active' : ''}
            onClick={() => this.handleTabChange('specialtyProgram')}
          >
            소상공인특화
          </button>
          <button
            className={activeTab === 'growthProgram' ? 'active' : ''}
            onClick={() => this.handleTabChange('growthProgram')}
          >
            성장지원
          </button>
        </div>
        <div className="tab-content">
          {activeTab === 'specialtyProgram' && <SpecialtyProgramsList />}
          {activeTab === 'growthProgram' && <GrowthProgramsList />}
        </div>
      </div>
    );
  }
}

export default SupportProgramsPage;
